import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WotdComponent } from './wotd.component';

describe('WotdComponent', () => {
  let component: WotdComponent;
  let fixture: ComponentFixture<WotdComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WotdComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WotdComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
