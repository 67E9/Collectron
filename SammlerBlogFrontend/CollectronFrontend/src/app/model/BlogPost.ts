import {Collectible} from "./Collectible";

export interface BlogPost {
  id?: number;
  title: string;
  article: string;
  timeStamp?: Date;
  collectible: Collectible;
  selected?: boolean
}
