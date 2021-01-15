import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { ImageModel } from '../../../../../app/core/model/image-model';
import { ClientImage } from '../../../../../app/core/model/client-image';

@Component({
  selector: 'app-carousel',
  templateUrl: './carousel.component.html',
  styleUrls: ['./carousel.component.scss']
})
export class CarouselComponent implements OnInit {

  @Input()
  images: ClientImage[];

  @Input()
  imagesLoading: boolean;

  @Input()
  enableAddAndRemove: boolean;

  @Output()
  insertImageLocal: EventEmitter<ClientImage> = new EventEmitter<ClientImage>();

  @Output()
  removeImageLocal: EventEmitter<number> = new EventEmitter<number>();

  activeSlideIndex: number = 0;
  fileButtonWidth: any;
  defaultImagepath: string = "../../../../assets/images/empty.png";

  constructor() { }

  ngOnInit(): void {}

  calculateFileButtonWidth(path: string){
    const img = new Image();
    let that = this;
    img.onload = function() {
      if(img.width > 280)
        that.fileButtonWidth = 280;
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
  }

  deselectImage(): void{
    this.removeImageLocal.emit(this.activeSlideIndex);
    this.calculateFileButtonWidth(this.defaultImagepath);
    if(this.activeSlideIndex != 0)
      this.activeSlideIndex -= 1;
  }

}
