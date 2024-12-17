<template>
  <div class="expense-list">
    <h2>Expenses</h2>
    <div>
      <label for="categoryFilter">Category:</label>
      <select v-model="categoryFilter" @change="filterExpenses">
        <option value="">All</option>
        <option value="FOOD">Food</option>
        <option value="TRANSPORT">Transport</option>
        <option value="ENTERTAINMENT">Entertainment</option>
        <option value="MISCELLANEOUS">MISCELLANEOUS</option>
        <option value="UTILITIES"></option>
        <!-- Add other categories as needed -->
      </select>
    </div>

    <ul>
      <li v-for="expense in filteredExpenses" :key="expense.id">
        <p>*
          {{ expense.title }} - ${{ expense.amount }} ({{ expense.category }})</p>
        <button @click="deleteExpense(expense.id)">Delete</button>
        <button @click="editExpense(expense)">Edit</button>
      </li>
    </ul>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex';

export default {
  data() {
    return {
      categoryFilter: '',
    };
  },
  computed: {
    ...mapState(['expenses']),
    filteredExpenses() {
      if (this.categoryFilter) {
        return this.expenses.filter(expense => expense.category === this.categoryFilter);
      }
      return this.expenses;
    }
  },
  methods: {
    ...mapActions(['fetchExpenses', 'deleteExpense']),
    
    async deleteExpense(expenseId) {
      await this.$store.dispatch('deleteExpense',expenseId)
    },
    
    editExpense(expense) {
      // Redirect to edit page or open edit form with existing expense details
      this.$router.push({ name: 'editExpense', params: { id: expense.id } });
    },
  },
  mounted() {
    this.fetchExpenses();
  }
};
</script>

<style scoped>
.expense-list {
  max-width: 600px; /* Set a maximum width for the expense list */
  margin: 20px auto; /* Center the list on the page */
  padding: 20px; /* Add some padding */
  border: 1px solid #ccc; /* Add a light border */
  border-radius: 8px; /* Rounded corners */
  background-color: #f9f9f9; /* Light background color */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
}

h2 {
  text-align: center; /* Center the heading */
  color: #333; /* Darker color for the heading */
}

label {
  font-weight: bold; /* Make the label bold */
  margin-right: 10px; /* Space between label and dropdown */
}

select {
  padding: 5px; /* Add some padding to the dropdown */
  border: 1px solid #ccc; /* Light border for the dropdown */
  border-radius: 4px; /* Rounded corners */
  margin-bottom: 20px; /* Space below the dropdown */
}

ul {
  list-style-type: none; /* Remove default list styling */
  padding: 0; /* Remove padding */
}

li {
  display: flex; /* Use flexbox for layout */
  justify-content: space-between; /* Space between items */
  align-items: center; /* Center items vertically */
  padding: 10px; /* Add padding to each list item */
  border-bottom: 1px solid #eee; /* Light border between items */
}

li:last-child {
  border-bottom: none; /* Remove border from the last item */
}

p {
  margin: 0; /* Remove default margin */
  color: #555; /* Slightly lighter color for expense text */
}

button {
  padding: 5px 10px; /* Add padding to buttons */
  border: none; /* Remove default border */
  border-radius: 4px; /* Rounded corners */
  cursor: pointer; /* Change cursor to pointer */
  transition: background-color 0.3s; /* Smooth transition for hover effect */
}

button:hover {
  background-color: #007bff; /* Change background color on hover */
  color: white; /* Change text color on hover */
}

button:focus {
  outline: none; /* Remove outline on focus */
}
</style>
