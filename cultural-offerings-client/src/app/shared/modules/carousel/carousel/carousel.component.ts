import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ClientImage } from '../../../../../app/core/model/client-image';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {

  @Input()
  get images(){ return this._images; }
  set images(value: ClientImage[]){
    this._images = value;
    if(this._images && this._images.length != 0)
      this.calculateFileButtonWidth(this._images[this.activeSlideIndex].retrievedImage);
    else{
      this.calculateFileButtonWidth(this.defaultImagepath);
    }
  }
  private _images: ClientImage[];

  @Input()
  imagesLoading: boolean;

  @Input()
  enableAddAndRemove: boolean;

  @Input()
  maxImageWidth: number = 600;

  @Input()
  maxImageHeight: number = 600;

  @Output()
  insertImageLocal: EventEmitter<ClientImage> = new EventEmitter<ClientImage>();

  @Output()
  removeImageLocal: EventEmitter<number> = new EventEmitter<number>();

  activeSlideIndex: number = 0;
  fileButtonWidth: any;
  defaultImagepath: string = "../../../../assets/images/empty.png";

  constructor() { }

  ngOnInit(): void {
    if(this.enableAddAndRemove){
      if(this.images.length > 0){
        this.calculateFileButtonWidth(this.images[this.activeSlideIndex].retrievedImage);
      }else{
        this.calculateFileButtonWidth(this.defaultImagepath);
      }
    }
  }

  calculateFileButtonWidth(path: string){
    const img = new Image();
    let that = this;
    img.onload = function() {
      if(img.width > 280)
        that.fileButtonWidth = that.maxImageWidth;
      else
        that.fileButtonWidth = img.width;
    }
    img.src = path;
  }

  onFileChanged(event): void{
    var reader = new FileReader();
    let that = this;
    const selectedFile: File = event.target.files[0];
    reader.onload = function (e) {
      const retrievedImage: any = e.target.result;
      that.insertImageLocal.emit({retrievedImage, selectedFile});
      that.calculateFileButtonWidth(retrievedImage);
      that.activeSlideIndex = that.images.length - 1;
    };
    reader.readAsDataURL(selectedFile);
    event.target.value = "";
  }

  deselectImage(): void{
    this.removeImageLocal.emit(this.activeSlideIndex);
    if(this.activeSlideIndex != 0)
      this.activeSlideIndex -= 1;
    if(this.images.length == 0)
      this.calculateFileButtonWidth(this.defaultImagepath);
    else
      this.calculateFileButtonWidth(this.images[this.activeSlideIndex].retrievedImage);
  }

  next(){
    if(this.activeSlideIndex == this.images.length - 1)
      this.activeSlideIndex = 0;
    else
      this.activeSlideIndex += 1;
  }

  prev(){
    if(this.activeSlideIndex == 0)
      this.activeSlideIndex = this.images.length - 1;
    else
      this.activeSlideIndex -= 1;
  }

}
