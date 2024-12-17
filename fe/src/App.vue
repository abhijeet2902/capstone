<template>
   <div id="app">
    <nav v-if="user">
      <ul class="navbar">
        <li><router-link to="/dashboard">Expense List</router-link></li>
        <li><router-link to="/expenses/add">Add an Expense</router-link></li>
        <li><router-link to="/report">Report</router-link></li>
        <li><button @click="logout">Logout</button></li>
      </ul>
    </nav>
    
    <router-view></router-view>
    </div>
</template>

<script>

export default {

   computed: {
    user() {
      return this.$store.state.user;
    },
  },

   methods: {
    
    logout() {
      this.$store.commit('SET_USER', null); // Clear user data from Vuex
      
      this.$router.push({ name: 'login' }); // Redirect to login page
    },
  },
};

</script>
<style>
#app {
  font-family: 'Avenir', 'Helvetica', 'Arial', sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #34495e;
  background-color: #f4f4f4;
  min-height: 100vh;
  padding: 20px;
}

nav {
  padding: 20px;
  background-color: #2c3e50;
  border-radius: 5px;
  margin-bottom: 20px;
}

nav a {
  font-weight: bold;
  color: #ecf0f1;
  transition: color 0.3s;
}

nav a.router-link-exact-active {
  color: #1abc9c;
}

.navbar {
  list-style-type: none;
  padding: 0;
  margin: 0;
}

.navbar li {
  display: inline-block;
  margin: 0 15px;
}

.navbar li a,
.navbar li button {
  color: #ecf0f1;
  text-decoration: none;
  padding: 10px 20px;
  background-color: transparent;
  border: 2px solid transparent;
  border-radius: 4px;
  transition: background-color 0.3s, border-color 0.3s;
}

.navbar li button {
  background-color: #2c3e50;
}

.navbar li button:hover,
.navbar li a:hover {
  background-color: #34495e;
  border-color: #1abc9c;
}

button {
  cursor: pointer;
}

button:hover {
  background-color: #e0e0e0;
}
</style>