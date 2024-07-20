import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterModule } from '@angular/router';
import { SharedModule } from '../../components/shared/shared.module';

@Component({
    selector: 'app-sidebar',
    standalone: true,
    imports: [RouterModule, SharedModule],
    templateUrl: './sidebar.component.html',
    styleUrl: './sidebar.component.css'
})
export class SidebarComponent implements OnInit {

    public routes: any[] = [
        {
            title: 'Usuarios',
            icon: 'users',
            link: ['/users'],
            active: false,
        },
        {
            title: 'Cursos',
            icon: 'book',
            link: ['/courses'],
        }
    ];

    private router: Router;

    constructor(router: Router) {
        this.router = router;
    }

    ngOnInit(): void { }
}