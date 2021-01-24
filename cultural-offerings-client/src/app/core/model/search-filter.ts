export interface SearchFilter {
  term: string,
  culturalOfferingTypeIds: number[],
  culturalOfferingSubtypeIds: number[],
  subscriptions: boolean
}