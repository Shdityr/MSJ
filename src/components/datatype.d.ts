export interface Merchant {
    id: number;
    name: string;
    rating: number;
    averagePrice: number;
    location: string;
    images: string[];
    businessHours: string;
    contactInfo: string;
    reviewsId: number[];
    Style: string[];
    DishesId: number[];
    location_x: number;
    location_y: number;
  }

export interface MerchantInfo {
    id: number;
    name: string;
    rating: number;
    averagePrice: number;
    location: string;
    images: string[];
    businessHours: string;
    location_x: number;
    location_y: number;
  }

export interface Review {
    id: number;
    userID: number;
    contents: string;
    rating: number;
    averagePrice: number;
    location: string;
    images: string[];
    Style: string[];
  }

export interface Dish {
    id: number;
    name: string;
    rating: number;
    averagePrice: number;
    images: string[];
    reviewsId: number[];
    Style: string[];
  }

  export interface User {
    id: number;
    username: string;
    userAvatar: string;
    password: number;
  }