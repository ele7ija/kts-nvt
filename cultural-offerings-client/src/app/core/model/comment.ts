export interface Comment {
  id?: number;
  text: string;
  date: Date;
  culturalOfferingId: number;
  userId: number;
  imageIds: number[];
}
