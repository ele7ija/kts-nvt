import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { By } from '@angular/platform-browser';

import { SimpleSnackbarComponent } from './simple-snackbar.component';

describe('SimpleSnackbarComponent', () => {
  let component: SimpleSnackbarComponent;
  let fixture: ComponentFixture<SimpleSnackbarComponent>;

  const message = 'Poruka';
  const title = 'Naslov';
  const success: boolean = true;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SimpleSnackbarComponent ],
      providers: [
        {
          provide: MatSnackBarRef,
          useValue: {}
        }, 
        {
          provide: MAT_SNACK_BAR_DATA,
          useValue: {message, title, success}
        }
      ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SimpleSnackbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have sync html with component variables', () => {
    const htmlTitle = fixture.debugElement.query(By.css('h3')).nativeElement;
    const htmlMessage = fixture.debugElement.query(By.css('p')).nativeElement;
    
    expect(htmlTitle.innerHTML).toBe(title);
    expect(htmlMessage.innerHTML).toBe(message);
    
    component.data.message = 'Nova poruka';
    fixture.detectChanges();

    expect(htmlMessage.innerHTML).toBe('Nova poruka');
  });
});
