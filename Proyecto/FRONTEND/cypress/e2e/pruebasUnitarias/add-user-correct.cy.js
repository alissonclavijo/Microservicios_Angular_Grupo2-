describe('add user test', () => {
  it('Deberia ingresar usuario llenando todos los campos', () => {
    cy.visit('/users');

    // Ajustar el selector para encontrar el input de nombre correctamente
    cy.get('input[name="name"]').type('alisson');

    // Ajustar el selector para encontrar el input de email correctamente
    cy.get('input[name="email"]').type('alisson@espe.edu.ec');

    // Ajustar el selector para encontrar el input de password correctamente
    cy.get('input[name="password"]').type('1234');

    // Aquí puedes agregar un selector para el botón de envío y hacer clic en él
    cy.get('button[type="submit"]').click();
  });
});
