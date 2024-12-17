<!-- src/components/ReportComponent.vue -->
<template>
  <div class="report">
    <h2>Expense Report</h2>
    <p>Total Expenses: ${{ totalExpenses }}</p>
    <div>
      <h3>Expenses by Category</h3>
      <ul>
        <li v-for="(total, category) in expensesByCategory" :key="category">
          {{ category }}: ${{ total }}
        </li>
      </ul>
    </div>
    <!-- Optionally, integrate charts here -->
  </div>
</template>

<script>
import { mapGetters } from 'vuex';

export default {
  computed: {
    ...mapGetters(['totalExpenses', 'expensesByCategory']),
  },
};
</script>

<style scoped>
/* Styling for the report component */
</style>
