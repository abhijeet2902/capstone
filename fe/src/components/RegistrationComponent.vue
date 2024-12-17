<template>
    <div class="register-form">
      <h2>Register</h2>
      <form @submit.prevent="registerUser">
       
        <div>
          <label for="name">Username:</label>
          <input type="text" id="name" v-model="form.userName" required />
        </div>
  
        <div>
          <label for="phone">Phone Number:</label>
          <input type="tel" id="phone" v-model="form.phoneNumber" required />
        </div>
  
        <div>
          <label for="email">Email:</label>
          <input type="email" id="email" v-model="form.email" required />
        </div>
  
        <div>
          <label for="password">Password:</label>
          <input type="password" id="password" v-model="form.password" required />
        </div>
  
        <button type="submit">Register</button>
      </form>
    </div>
  </template>
  
  <script>
  import router from '@/router';
import axios from 'axios';
  
  export default {
    name: "RegisterForm",
    data() {
      return {
        form: {
          userName: "",
          phoneNumber: "",
          email: "",
          password: "",
        },
      };
    },
    methods: {
      async registerUser() {
        try {
          const response = await axios.post("http://localhost:8080/api/users", this.form);
          alert("Registration Successful!");
          console.log(response.data);
          router.push("/")
        } catch (error) {
          console.error("Error while registering user:", error);
          alert("Registration failed. Please try again.");
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .register-form {
    max-width: 400px;
    margin: 0 auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  label {
    display: block;
    margin: 10px 0 5px;
    font-weight: bold;
  }
  
  input {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
  
  button {
    width: 100%;
    padding: 10px;
    background-color:black;
    color: green;
    border: none;
    border-radius: 5px;
    font-size: 16px;
    cursor: pointer;
  }
  
  button:hover {
    background-color:blue;
  }
  </style>
  