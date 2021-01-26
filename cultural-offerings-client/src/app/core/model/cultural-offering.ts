export interface CulturalOffering {
  id: number;
  name: string;
  description: string;
  locationId: number;
  locationName: string;
  latitude: number;
  longitude: number;
  culturalOfferingTypeName: string;
  culturalOfferingSubtypeName: string;
  imageIds: number[];
}
