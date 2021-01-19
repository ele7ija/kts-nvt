import { CommonModule } from '@angular/common';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatDialogModule, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { By } from '@angular/platform-browser';
import { CarouselProps } from 'src/app/core/model/carousel-props';
import { CarouselWrapperModule } from '../../carousel/carousel.module';

import { CarouselDialogComponent } from './carousel-dialog.component';

describe('CarouselDialogComponent', () => {
  let component: CarouselDialogComponent;
  let fixture: ComponentFixture<CarouselDialogComponent>;

  const carouselProps: CarouselProps = {
    enableAddAndRemove: false,
    images: [{retrievedImage: null}, {retrievedImage: null}],
    imagesLoading: false
  }

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CarouselDialogComponent ],
      imports: [
        CommonModule,
        MatDialogModule,
        CarouselWrapperModule
      ],
      providers: [
        {
          provide: MatDialogRef,
          useValue: {}
        },
        {
          provide: MAT_DIALOG_DATA,
          useValue: carouselProps
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CarouselDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should pass data to carousel component', () => {
    const carouselComponent = fixture.debugElement.query(By.css('app-carousel')).componentInstance;
    expect(carouselComponent.enableAddAndRemove).toBe(carouselProps.enableAddAndRemove);
    expect(carouselComponent.images).toBe(carouselProps.images);
    expect(carouselComponent.imagesLoading).toBe(carouselProps.imagesLoading);
  })
});
