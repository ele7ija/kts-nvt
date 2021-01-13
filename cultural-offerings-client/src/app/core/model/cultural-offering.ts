export interface CulturalOffering{
  id: number;
  name: string;
  description: string;
  locationId: number;
  culturalOfferingTypeName: string;
  culturalOfferingSubtypeName: string;
  imageIds: number[];
}