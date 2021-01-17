import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ImageFileChipsComponent } from './image-file-chips.component';

describe('ImageFileChipsComponent', () => {
  let component: ImageFileChipsComponent;
  let fixture: ComponentFixture<ImageFileChipsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ImageFileChipsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ImageFileChipsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
