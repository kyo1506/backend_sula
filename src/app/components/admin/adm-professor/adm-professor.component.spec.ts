import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmProfessorComponent } from './adm-professor.component';

describe('AdmProfessorComponent', () => {
  let component: AdmProfessorComponent;
  let fixture: ComponentFixture<AdmProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdmProfessorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdmProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
