import BaseResponse from 'models/BaseResponse';

export interface CategoryResponse extends BaseResponse {
  name: string;
  slug: string;
  description: string | null;
  thumbnail: string | null;
  status: number;
}


export interface CategoryRequest {
  name: string;
  slug: string;
  description: string | null;
  thumbnail: string | null;
  status: number;
}