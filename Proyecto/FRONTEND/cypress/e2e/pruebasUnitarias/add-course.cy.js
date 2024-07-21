describe('add course test', () => {
  it('Deberia ingresar el curso', () => {
    cy.visit('/courses');

    // Ingresar el nombre del curso
    cy.get('input[name="name"]').type('Curso de Prueba');

    cy.get('button[type="button"].btn.btn-secondary').click();

  });
});
