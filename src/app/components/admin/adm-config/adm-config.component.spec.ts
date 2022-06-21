import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmConfigComponent } from './adm-config.component';

describe('AdmConfigComponent', () => {
  let component: AdmConfigComponent;
  let fixture: ComponentFixture<AdmConfigComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdmConfigComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmConfigComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
