import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { MapInfoWindow, MapMarker, GoogleMap, MapAnchorPoint } from '@angular/google-maps'
import { MdbCheckboxChange } from 'angular-bootstrap-md';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { CulturalOffering } from 'src/app/core/model/cultural-offering';
import { CulturalOfferingSubtype } from 'src/app/core/model/cultural-offering-subtype';
import { CulturalOfferingType } from 'src/app/core/model/cultural-offering-type';
import { SearchFilter } from 'src/app/core/model/search-filter';
import { CulturalOfferingSubtypeService } from 'src/app/core/services/cultural-offering-subtype/cultural-offering-subtype.service';
import { CulturalOfferingTypeService } from 'src/app/core/services/cultural-offering-type/cultural-offering-type.service';
import { CulturalOfferingService } from 'src/app/core/services/cultural-offering/cultural-offering.service';

declare var ol: any;

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrls: ['./homepage.component.scss'],
})

export class HomepageComponent implements OnInit {
  @ViewChild(GoogleMap) map: GoogleMap;
  @ViewChild(MapInfoWindow) infoWindow: MapInfoWindow;
  @ViewChild('searchFilter') searchFilter;

  constructor(
    private culturalOfferingService: CulturalOfferingService,
    private culturalOfferingTypeService: CulturalOfferingTypeService,
    private culturalOfferingSubtypeService: CulturalOfferingSubtypeService,
    private elRef: ElementRef,
    private formBuilder: FormBuilder
  ) {
    this.searchFilterForm = formBuilder.group({
      "termField": [""]
    })
  }

  ngOnInit(): void {
    this.fetchAllCulturalOffering();
    this.fetchAllCulturalOfferingTypes();
  }

  addSearchFilter() {
    var div = this.elRef.nativeElement.querySelector('#searchFilter');
    if (this.map.controls[google.maps.ControlPosition.LEFT_TOP].getLength() == 0) {
      this.map.controls[google.maps.ControlPosition.LEFT_TOP].push(div)
    }
  }

  // Mapa
  center: google.maps.LatLngLiteral = {lat: 44, lng: 20.7};
  markerPositions: google.maps.LatLngLiteral[] = [];
  zoom = 7;
  options: google.maps.MapOptions = {
    mapTypeControl: false,
  }
  infoWindowOptions: google.maps.InfoWindowOptions;

  // Podaci
  culturalOfferings: CulturalOffering[] = [];
  culturalOfferingTypes: CulturalOfferingType[] = [];
  culturalOfferingSubtypes: Map<number, CulturalOfferingSubtype[]> = new Map<number, CulturalOfferingSubtype[]>();

  searchFilterForm: FormGroup;


  addMarker(event: google.maps.MapMouseEvent) {
    this.markerPositions.push(event.latLng.toJSON());
  }

  openInfoWindow(marker: MapMarker) {
    this.infoWindow.open(marker);
  }

  openPeekInfoWindow(marker: MapMarker, culturalOffering: CulturalOffering) {
    this.infoWindow.options = {content: this.formatInfoWindowContent(culturalOffering)}
    this.infoWindow.open(marker);
  }

  closePeekInfoWindow(marker: MapMarker, culturalOffering: CulturalOffering) {
    this.infoWindow.close();
  }
  formatInfoWindowContent(culturalOffering: CulturalOffering): string {
    let retval = '';
    retval += `<h4 style="opacity: 0.7">${culturalOffering.culturalOfferingTypeName} <b>/</b> ${culturalOffering.culturalOfferingSubtypeName}</h4>`
    retval += `<h2>${culturalOffering.name}</h2>`
    retval += `<h4>${culturalOffering.description}</h4>`
    // TODO SLIKE
    return retval;
  }

  async fetchAllCulturalOffering() {
    this.culturalOfferingService.getAll({
      page: 0,
      size: 1000,
      sort: '',
      sortOrder: ''
    })
    .subscribe((page) => {
      this.culturalOfferings = [...page.content];
    })
  }

  async fetchAllCulturalOfferingTypes() {
    this.culturalOfferingTypeService.getAll({
      page: 0,
      size: 100,
      sort: '',
      sortOrder: ''
    })
    .subscribe((page) => {
      this.fetchAllCulturalOfferingSubtypes(page.content);
      this.culturalOfferingTypes = [...page.content];
      for (let culturalOfferingType of this.culturalOfferingTypes) {
        let formControlName = `type${culturalOfferingType.id}`
        this.searchFilterForm.addControl(formControlName, new FormControl());
        let temp = {};
        temp[formControlName] = true;
        this.searchFilterForm.patchValue(temp)
      }
    })
  }

  fetchAllCulturalOfferingSubtypes(types: CulturalOfferingType[]) {
    for (let culturalOfferingType of types) {
      this.culturalOfferingSubtypeService.getAllByTypeId(culturalOfferingType.id)
      .subscribe((culturalOfferingSubtypes: CulturalOfferingSubtype[]) => {
        this.culturalOfferingSubtypes.set(culturalOfferingType.id, culturalOfferingSubtypes)
        for (let culturalOfferingSubtype of this.culturalOfferingSubtypes.get(culturalOfferingType.id)) {
          let formControlName = `subtype${culturalOfferingSubtype.id}`;
          this.searchFilterForm.addControl(formControlName, new FormControl());
          let temp = {};
          temp[formControlName] = true;
          this.searchFilterForm.patchValue(temp)
        }
      })
    }
  }

  formatPosition(culturalOffering: CulturalOffering) {
    return new google.maps.LatLng(
      culturalOffering.latitude,
      culturalOffering.longitude
    )
  }

  typeChecked(event: MdbCheckboxChange, culturalOfferingType: CulturalOfferingType) {
    let newValue = {};
    for (let culturalOfferingSubtype of this.culturalOfferingSubtypes.get(culturalOfferingType.id)) {
      if (event.checked) {
        newValue[`subtype${culturalOfferingSubtype.id}`] = true;
      }
      else {
        newValue[`subtype${culturalOfferingSubtype.id}`] = false;
      }
    }
    this.searchFilterForm.patchValue(newValue);
  }

  subtypeChecked(event: MdbCheckboxChange, culturalOfferingSubtype: CulturalOfferingSubtype) {
    let newValue = {};
    let typeId = culturalOfferingSubtype.typeId;
    if (event.checked) {
      for (let culturalOfferingSubtype of this.culturalOfferingSubtypes.get(typeId)) {
        if (this.searchFilterForm.value[`subtype${culturalOfferingSubtype.id}`] == false) {
          return;
        }
      }
      newValue[`type${typeId}`] = true;
    }
    else {
      newValue[`type${typeId}`] = false;
    }
    this.searchFilterForm.patchValue(newValue);
  }

  searchFilterApply() {
    console.log(this.searchFilterForm.value)
    let searchFilter: SearchFilter = {
      term: this.searchFilterForm.value["termField"],
      culturalOfferingSubtypeIds: [],
      culturalOfferingTypeIds: []
    };
    searchFilter.culturalOfferingTypeIds = [];
    searchFilter.culturalOfferingSubtypeIds = [];
    for (let val in this.searchFilterForm.value) {
      if (val.startsWith('type')) {
        if (this.searchFilterForm.value[val] == true) {
          let typeId = parseInt(val.substring(4));
          searchFilter.culturalOfferingTypeIds.push(typeId);
        }
      }
      else if (val.startsWith('subtype')) {
        if (this.searchFilterForm.value[val] == true) {
          let subtypeId = parseInt(val.substring(7));
        searchFilter.culturalOfferingSubtypeIds.push(subtypeId)
        }
      }
    }
    this.culturalOfferingService.searchFilter(
      searchFilter, {
      page: 0,
      size: 1000,
      sort: '',
      sortOrder: ''
    })
    .subscribe((page) => {
      this.culturalOfferings = [...page.content];
    })
  }
}
