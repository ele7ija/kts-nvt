export class CulturalOfferingType {
  public id?: number;
  public typeName!: string;
  public imageId?: number;
  public subTypeIds?: number[];
}

export class CulturalOfferingTypeUpsert extends CulturalOfferingType {
  public subTypesToAdd?: string[];
}
