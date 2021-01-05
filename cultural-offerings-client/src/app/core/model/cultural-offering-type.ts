export interface CulturalOfferingType {
  id?: number;
  typeName?: string;
  imageId?: number;
  subTypeIds?: number[];
}

export interface CulturalOfferingTypeUpsert extends CulturalOfferingType {
  subTypesToAdd?: string[];
}
