import { ClientImage } from "./client-image";

export interface CarouselProps {
  images: ClientImage[];
  imagesLoading: boolean;
  enableAddAndRemove: boolean;
  maxImageWidth?: number;
  maxImageHeight?: number;
}
