import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SModalComponent } from './s-modal.component';

describe('SModalComponent', () => {
  let component: SModalComponent;
  let fixture: ComponentFixture<SModalComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SModalComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SModalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
