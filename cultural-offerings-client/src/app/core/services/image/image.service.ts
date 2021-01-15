import { HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ImageModel } from 'src/app/core/model/image-model';
import { environment } from 'src/environments/environment';
import { ApiService } from '../security/api-service/api.service';

@Injectable()
export class ImageService {

  private endpoint: string = environment.baseUrl + '/images'

  constructor(private apiService: ApiService) {}

  getById(imageId: number): Observable<ImageModel>{
    return this.apiService.get(`${this.endpoint}/${imageId}`);
  }

  upload(selectedFile: File): Observable<ImageModel>{
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', selectedFile, selectedFile.name);

    /*const customHeaders: HttpHeaders = new HttpHeaders({
      'Content-Type': 'multipart/form-data'
    });*/

    return this.apiService.postFile(this.endpoint, uploadImageData);
  }

  uploadAsPromise(selectedFile: File): Promise<ImageModel>{
    const uploadImageData = new FormData();
    uploadImageData.append('imageFile', selectedFile, selectedFile.name);

    /*const customHeaders: HttpHeaders = new HttpHeaders({
      'Content-Type': 'multipart/form-data'
    });*/

    return this.apiService.postFile(this.endpoint, uploadImageData).toPromise();
  }

}

