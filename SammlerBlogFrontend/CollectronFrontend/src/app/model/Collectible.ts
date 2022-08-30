import {Type} from "./Type";
import {BlogPost} from "./BlogPost";

export interface Collectible {
    id?: number;
    name: string;
    description: string;
    estimatedValue: number;
    condition?: string;
    forSale: boolean;
    imageUrl: string;
    type?: Type;
    blogPosts: BlogPost[];
    selected?: boolean
}
