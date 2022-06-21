import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewAgeModComponent } from './new-age-mod.component';

describe('NewAgeModComponent', () => {
  let component: NewAgeModComponent;
  let fixture: ComponentFixture<NewAgeModComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewAgeModComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NewAgeModComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
