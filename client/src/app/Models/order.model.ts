import { Article } from "./article.model";

export interface Order{
    reference : String,
    date : Date,
    articles : Article[]
}