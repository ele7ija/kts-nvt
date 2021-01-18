export interface CulturalOffering {
  id: number;
  name: string;
  description: string;
  locationId: number;
  longitude: number;
  latitude: number;
  locationName: string;
  culturalOfferingTypeName: string;
  culturalOfferingSubtypeName: string;
  imageIds: string[];
}