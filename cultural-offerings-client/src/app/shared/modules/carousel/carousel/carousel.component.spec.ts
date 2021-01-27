import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CarouselModule, WavesModule } from 'angular-bootstrap-md';
import { CarouselProps } from 'src/app/core/model/carousel-props';

import { CarouselComponent } from './carousel.component';

describe('CarouselComponent', () => {
  let component: CarouselComponent;
  let fixture: ComponentFixture<CarouselComponent>;

  const carouselProps: CarouselProps = {
    enableAddAndRemove: true,
    maxImageHeight: 400,
    maxImageWidth: 600,
    images: [{retrievedImage: null}, {retrievedImage: null}],
    imagesLoading: false
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarouselComponent ],
      imports: [
        CommonModule,
        CarouselModule,
        WavesModule
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarouselComponent);
    component = fixture.componentInstance;
    component.images = [];
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should change active slide index', () => {
    component.images = carouselProps.images;
    let activeSlideIndex = component.activeSlideIndex;
    component.next();
    expect(component.activeSlideIndex).toBe(activeSlideIndex + 1);

    activeSlideIndex = component.activeSlideIndex;
    component.prev();
    expect(component.activeSlideIndex).toBe(activeSlideIndex - 1);
  });

  it('should recalculate file button width', () => {
    spyOn(component, 'calculateFileButtonWidth');
    component.images = carouselProps.images;
    component.deselectImage();
    expect(component.calculateFileButtonWidth).toHaveBeenCalled();
  });

});
