export class UserProfile {
    id?: number;
    name: string;
    role: string;
    password?: string;

    constructor(
        name: string,
        role: string
    ){
        this.name = name;
        this.role = role;
    }
}