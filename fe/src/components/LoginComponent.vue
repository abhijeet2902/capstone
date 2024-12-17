<!-- src/components/LoginComponent.vue -->
<template>
  <div class="login">
    <h2>Login</h2>
    <form @submit.prevent="loginUser">
      <div>
        <label for="username">Username:</label>
        <input v-model="form.userName" type="text" id="username" required />
      </div>
      <div>
        <label for="password">Password:</label>
        <input v-model="form.password" type="password" id="password" required />
      </div>
      <button type="submit">Login</button>
      <router-link to="/register">New User Register Here</router-link>
    </form>
    <p v-if="errorMessage" class="error">{{ errorMessage }}</p>
  </div>
</template>

<script>
import axios from 'axios';
import { mapActions } from 'vuex';

export default {
  data() {
    return {
      form:{
        userName: '',
      password: '',
      }
     
    };
  },
  methods: {
    ...mapActions(['fetchUser']),
    
    async loginUser() {
  try {
    console.log('Login request initiated with:');
    console.log('Username:', this.form.userName);
    console.log('Password:', this.form.password);

    
    // const requestUrl = `http://localhost:8080/api/users/login,${this.username,this.password}`;
    // console.log('Request URL:', requestUrl);
    
    
    const response = await axios.post('http://localhost:8080/api/users/login',this.form);
    
   
    console.log('Response:', response);
    this.$store.commit('SET_USER', response.data);
    this.$router.push({ name: 'expenseList' }); 
  } catch (error) {
    console.error('Error occurred:', error);
    this.errorMessage = 'Invalid credentials. Please try again.';
  }
}

  }
};
</script>

<style scoped>
.login {
  max-width: 400px; /* Set a maximum width for the form */
  margin: 50px auto; /* Center the form on the page */
  padding: 20px; /* Add some padding */
  border: 1px solid #ccc; /* Add a light border */
  border-radius: 8px; /* Rounded corners */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1); /* Subtle shadow for depth */
  background-color: #f9f9f9; /* Light background color */
}

h2 {
  text-align: center; /* Center the heading */
  color: #333; /* Darker color for the heading */
}

form {
  display: flex; /* Use flexbox for layout */
  flex-direction: column; /* Stack elements vertically */
}

div {
  margin-bottom: 15px; /* Space between form elements */
}

label {
  margin-bottom: 5px; /* Space between label and input */
  font-weight: bold; /* Make labels bold */
  color: #555; /* Slightly lighter color for labels */
}

input {
  padding: 10px; /* Padding inside input fields */
  border: 1px solid #ccc; /* Light border for input fields */
  border-radius: 4px; /* Rounded corners for input fields */
  font-size: 16px; /* Font size for input text */
}

input:focus {
  border-color: #007bff; /* Change border color on focus */
  outline: none; /* Remove default outline */
}

button {
  padding: 10px; /* Padding inside the button */
  background-color: #007bff; /* Primary button color */
  color: white; /* Text color for button */
  border: none; /* Remove border */
  border-radius: 4px; /* Rounded corners for button */
  font-size: 16px; /* Font size for button text */
  cursor: pointer; /* Change cursor to pointer on hover */
}

button:hover {
  background-color: #0056b3; /* Darker shade on hover */
}

.router-link {
  text-align: center; /* Center the register link */
  margin-top: 10px; /* Space above the link */
  display: block; /* Make it a block element */
  color: #007bff; /* Color for the link */
  text-decoration: none; /* Remove underline */
}

.router-link:hover {
  text-decoration: underline; /* Underline on hover */
}

.error {
  color: red; /* Red color for error messages */
  text-align: center; /* Center the error message */
  margin-top: 10px; /* Space above the error message */
}
</style>
