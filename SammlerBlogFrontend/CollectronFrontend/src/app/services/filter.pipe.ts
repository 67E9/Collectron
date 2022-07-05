import {Pipe, PipeTransform} from "@angular/core";

@Pipe({
  name: 'filter'
})
export class FilterPipe implements PipeTransform {
  transform(itemList: any, searchKeyword: string) {
    if (!itemList)
      return [];
    if (!searchKeyword)
      return itemList;
    let filteredList: any[] = [];
    if (itemList.length > 0) {
      searchKeyword = searchKeyword.toLowerCase();
      itemList.forEach( (item:any) => {
        //Object.values(item) => gives the list of all the property values of the 'item' object
        let propValueList: any[] = Object.values(item);
        for(let i=0;i<propValueList.length;i++)
        {
          if (propValueList[i]) {
            if (propValueList[i].toString().toLowerCase().indexOf(searchKeyword) > -1)
            {
              filteredList.push(item);
              break;
            }
          }
        }
      });
    }
    return filteredList;
  }
}
