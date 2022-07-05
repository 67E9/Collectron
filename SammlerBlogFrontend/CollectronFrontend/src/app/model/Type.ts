import {Collectible} from "./Collectible";

export interface Type {
  id?: number;
  name: string;
  description: string;
  collectibles: Collectible[];
  selected?: boolean
}
