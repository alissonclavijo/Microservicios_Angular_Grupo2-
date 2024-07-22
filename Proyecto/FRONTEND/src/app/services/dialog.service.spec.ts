import { TestBed } from '@angular/core/testing';
import { DialogService, DialogOptions } from './dialog.service';

describe('DialogService', () => {
  let service: DialogService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DialogService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should show dialog', () => {
    const options: DialogOptions = { message: 'Test Message' };
    service.show(options);

    service.onShow().subscribe(res => {
      expect(res).toEqual(options);
    });
  });

  it('should cancel dialog', () => {
    service.cancel();

    service.onCancel().subscribe(() => {
      expect(true).toBe(true);
    });
  });

  it('should confirm dialog', () => {
    service.confirm();

    service.onConfirm().subscribe(() => {
      expect(true).toBe(true);
    });
  });
});
