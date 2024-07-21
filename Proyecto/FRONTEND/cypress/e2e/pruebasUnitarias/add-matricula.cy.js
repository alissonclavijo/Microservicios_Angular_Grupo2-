describe('add course test', () => {
  it('Deberia seleccionar y matricular al estudiante en el curso', () => {
    cy.visit('/enrollment');

    // Seleccionar un usuario
    cy.get('select#userSelect').select('Ali');

    // Seleccionar un curso
    cy.get('select#courseSelect').select('Artes');

    // Hacer clic en el botón de matricular
    cy.get('button.btn.btn-primary.mt-3').click();

  });
});
