"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
require("./App.css");
const react_router_dom_1 = require("react-router-dom");
const LoginPage_1 = require("./pages/LoginPage");
const RegisterPage_1 = require("./pages/RegisterPage");
const DashboardPage_1 = require("./pages/DashboardPage");
const App = () => (<react_router_dom_1.BrowserRouter>
    <react_router_dom_1.Routes>
      <react_router_dom_1.Route path="/" element={<LoginPage_1.LoginPage />}/>
      <react_router_dom_1.Route path="/register" element={<RegisterPage_1.RegisterPage />}/>
      <react_router_dom_1.Route path="/dashboard" element={<DashboardPage_1.DashboardPage />}/>
    </react_router_dom_1.Routes>
  </react_router_dom_1.BrowserRouter>);
exports.default = App;
