import { Component, OnInit } from '@angular/core';
import { CourseService } from '../../services/course.service';
import { UserService } from '../../services/user.service';
import { FormsModule } from '@angular/forms';
import { Course } from '../../models/course.model';
import { User } from '../../models/user.model';
import { CourseUser } from '../../models/courseUser.model';
import { lastValueFrom } from 'rxjs';
import { CommonModule } from '@angular/common';

interface CourseTableItem {
    course: any;
}

interface CourseForm {
    id: number;
    name: string;
    courseUsers: CourseUser[];
    users: User[];
}

@Component({
    selector: 'app-courses',
    standalone: true,
    imports: [FormsModule, CommonModule],
    templateUrl: './courses.component.html',
    styleUrl: './courses.component.css'
})
export class CoursesComponent implements OnInit{

    public courseRows: CourseTableItem[] = [];
    public userRows: User[] = [];
    public showFormModal = false;
    public courses: Course[] = [];
    
    public courseForm: CourseForm = {
        id: 0,
        name: '',
        courseUsers: [],
        users: []
    };

    public userForm: User = {
        id: 0,
        name: '',
        email: ''
    };
    

    constructor(private courseService: CourseService,
                private userService: UserService
                ) { }

    ngOnInit(): void {
        this.loadCourses();
        this.loadUsers();

        try {
            this.courseService.getCourse().subscribe((courses: Course[]) => {
                this.courses = courses;
                console.log(this.courses);
            });
        } catch (error) {
            console.error('Ocurrió un error al obtener los cursos');
            console.error(error);
        }
    }

    public async createCourse() {
        try {
            const course = await lastValueFrom(
                this.courseService.createCourse(this.courseForm)
            );
            console.log(course);
            this.courseRows.push({ course });
            await this.loadCourses();
        } catch (error) {
            console.error('Ocurrió un error al crear el curso');
            console.error(error);
        }
    }

    public async updateCourse() {
        try {
            const course = await lastValueFrom(
                this.courseService.updateCourse(this.courseForm)
            );
            console.log(course);
            await this.loadCourses();
        } catch (error) {
            console.error('Ocurrió un error al actualizar el curso');
            console.error(error);
        }
    }

    public async deleteCourse(id: number) {
        try {
            await lastValueFrom(
                this.courseService.deleteCourse(id)
            );
            this.courseRows = this.courseRows.filter((row) => row.course.id !== id);
            await this.loadCourses();
        } catch (error) {
            console.error('Ocurrió un error al eliminar el curso');
            console.error(error);
        }
    }

    private async loadCourses() {
        try {
            const courses = await lastValueFrom(this.courseService.getCourse());
            this.courseRows = courses.map((course) => ({ course }));
        } catch (error) {
            console.error('Ocurrió un error al obtener los cursos');
            console.error(error);
        }
    }

    private async loadUsers() {
        try {
            const users = await lastValueFrom(this.userService.getUsers());
            this.userRows = users;
            console.log(users);
        } catch (error) {
            console.error('Ocurrió un error al obtener los usuarios');
            console.error(error);
        }
    }

    public openFormModal(course: Course) {
        if (course) {
            this.courseForm = {
                id: course.id ?? 0,
                name: course.name ?? '',
                courseUsers: course.courseUsers ?? [],
                users: course.users ?? []
            };
        }
     }

}
