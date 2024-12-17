import { createRouter, createWebHistory } from "vue-router";
import LoginComponent from "../components/LoginComponent.vue";
import ExpenseListComponent from "../components/ExpenseListComponent.vue";
import ExpenseFormComponent from "../components/ExpenseFormComponent.vue";
import ReportComponent from "../components/ReportComponent.vue";
import RegistrationComponent from "@/components/RegistrationComponent.vue";

const routes = [
  {
    path: "/",
    name: "login",
    component: LoginComponent,
  },
  {
    path: "/dashboard",
    name: "expenseList",
    component: ExpenseListComponent,
  },
  {
    path: "/expenses/add",
    name: "addExpense",
    component: ExpenseFormComponent,
  },
  {
    path: "/expenses/edit/:id",
    name: "editExpense",
    component: ExpenseFormComponent,
  },
  {
    path: "/report",
    name: "report",
    component: ReportComponent,
  },
  {
    path:"/register",
    name:"register",
    component:RegistrationComponent
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
