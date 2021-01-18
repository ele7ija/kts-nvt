export interface User {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  password?: string;
  userRole?: string;
  newsIds?: [];
  commentIds?: [];
  subscriptionIds?: [];
  ratingIds?: [];
}
