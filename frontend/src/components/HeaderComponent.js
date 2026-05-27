import React from "react";

const HeaderComponent = () => {
  return (
    <header>
      <nav className="navbar navbar-expand-md navbar-dark bg-dark">
        <div>
          <a href="/employees" className="navbar-brand ms-3">
            Employee Management Application
          </a>
        </div>
      </nav>
    </header>
  );
};

export default HeaderComponent;