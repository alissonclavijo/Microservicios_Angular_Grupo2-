describe('add user test', () => {
    it('Deberia ingresar usuario', () => {
      cy.visit('/users');
  
      // Ajustar el selector para encontrar el input de nombre correctamente
      cy.get('input[name="name"]').type('Alisson');
    });
  });
  