import { Word } from './word';

export interface DictionaryResponse {
    words: Word[],
    totalResponseSize: number
}