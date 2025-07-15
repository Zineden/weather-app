export type City = {
    name: string;
    country: string;
    latitude: number;
    longitude: number;
    timezone: string;
    admin1: string;
    admin2?: string | null;
    admin3?: string | null;
    admin4?: string | null;
};