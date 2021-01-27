import { AfterViewInit, Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AbstractCrudService } from 'src/app/core/model/abstract-crud-service';
import { User } from 'src/app/core/model/user';
import { UserService } from 'src/app/core/services/user/user.service';
import { TableComponent } from 'src/app/shared/modules/table/table/table.component';

@Component({
  selector: 'app-user-table',
  templateUrl: './user-table.component.html',
  styleUrls: ['./user-table.component.scss'],
  providers: [
    {
      provide: AbstractCrudService,
      useClass: UserService
    }
  ],
})
export class UserTableComponent extends TableComponent<User> implements AfterViewInit {

  lastDeleted: User;

  constructor(
    private userService: AbstractCrudService<User>,
    private matSnackbar: MatSnackBar,
  )
  {
    super(userService, matSnackbar);
    this.displayedColumns = [{field: 'id', text: 'ID'}, {field: 'email', text: 'Email'}, {field: 'firstName', text: 'Ime'}, {field: 'lastName', text: 'Prezime'}, {field: 'userRole', text: 'Uloga'}, {field: 'Actions', text: 'Akcije'}];
  }

  ngAfterViewInit(): void{
    super.ngAfterViewInit();
  }

  async delete(entity: User): Promise<void>{
    this.lastDeleted = entity;
    super.delete(entity)
    .then(() => {
      this.lastDeleted = null;
      this.showSnackbar('USPESNO BRISANJE', `Korisnik sa e-mailom ${entity.email} je uspesno obrisan.`, true);
    })
    .catch(error => {
      this.lastDeleted = null;
      this.showSnackbar('NEUSPESNO BRISANJE', `${error.message}`, false);
    });
  }

}
