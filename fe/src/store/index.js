import { createStore } from "vuex";
import axios from 'axios';

const BASE_URL = "http://localhost:8080/api"
export default createStore({

  state: {
    user: null, 
    expenses: [], 
  },

  mutations: {
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',state.user)
    },
    SET_EXPENSES(state, expenses) {
      state.expenses = expenses;
    },
    ADD_EXPENSE(state, expense) {
      state.expenses.push(expense);
    },
    DELETE_EXPENSE(state, expenseId) {
      state.expenses = state.expenses.filter(expense => expense.id !== expenseId);
    },
  },

  actions: {
  
    async fetchUser({ commit }) {
      try {
        const response = await axios.get(`${BASE_URL}/users`);
        commit('SET_USER', response.data);
      } catch (error) {
        console.error('Error fetching user data:', error);
      }
    },

    async fetchExpenses({ commit }) {
      try {
        const response = await axios.get(`${BASE_URL}/expenses`);
        commit('SET_EXPENSES', response.data);
      } catch (error) {
        console.error('Error fetching expenses:', error);
      }
    },

    async addExpense({ commit }, expense) {
      try {
        console.log(expense);
        
        const response = await axios.post(`http://localhost:8080/api/expenses`, expense);
        commit('ADD_EXPENSE', response.data);
        
      } catch (error) {
        console.error('Error adding expense:', error);
      }
    },

    async deleteExpense({ commit }, expenseId) {
      try {
        await axios.delete(`${BASE_URL}/expenses/${expenseId}`);
        commit('DELETE_EXPENSE', expenseId);
      } catch (error) {
        console.error('Error deleting expense:', error);
      }
    },

    async editExpense({ commit }, expense) {
      try {
        const response = await axios.put(`${BASE_URL}/expenses/${expense.id}`, expense);
        commit('UPDATE_EXPENSE', response.data);
      } catch (error) {
        console.error('Error editing expense:', error);
      }
    },
},

  getters: {
    // Retrieve the total of all expenses
    totalExpenses(state) {
      return state.expenses.reduce((total, expense) => total + expense.amount, 0);
    },

    // Filter expenses by category
    expensesByCategory: (state) => (category) => {
      return state.expenses.filter(expense => expense.category === category);
    },
  },
});

