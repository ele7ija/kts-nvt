import { TestBed } from '@angular/core/testing';
import { ImageService } from './image.service';
import {of} from 'rxjs';
import { ApiService } from '../security/api-service/api.service';
import { HttpClient } from '@angular/common/http';

describe('ImageService', () => {
  let service: ImageService;

  const apiServiceStub = {
    get: jasmine.createSpy('get').and.returnValue(of({})),
    postFile: jasmine.createSpy('postFile').and.returnValue(of({}))
  };

  const httpClient = {};

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        ImageService,
        {provide: ApiService, useValue: apiServiceStub},
        {provide: HttpClient, useValue: httpClient}
      ]
    });
    service = TestBed.inject(ImageService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should inject dependencies', () => {
    expect(service.apiService).toBeTruthy();
  });

  it('should delegate get call', async () => {
    await service.getById(1).toPromise();
    expect(apiServiceStub.get).toHaveBeenCalledWith(`${service.endpoint}/1`);
  });

  it('should delegate post call', async () => {
    const obj = new File([], 'something');
    await service.upload(obj).toPromise();
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', obj, obj.name);
    expect(apiServiceStub.postFile).toHaveBeenCalledWith(service.endpoint, uploadImageData);
  });
});
