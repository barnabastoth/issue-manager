<template>
  <v-app>
    <v-toolbar app>
      <v-toolbar-title class="headline text-uppercase">
        <span>Due Date</span>
        <span class="font-weight-light"> Calculator</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn
        depressed
        color="primary"
        @click="openNewIssue()"
      >
        <v-icon left>fas fa-plus-circle</v-icon>
        <span class="mr-2">New Issue</span>
      </v-btn>
    </v-toolbar>

    <v-content>
      <Issues/>
    </v-content>

    <NewIssue :open.sync="showNewIssue"></NewIssue>

  </v-app>
</template>

<script>
import Issues from './components/Issues'
import NewIssue from './components/NewIssue'

export default {
  name: 'App',
  data () {
    return {
      showNewIssue: false
    }
  },
  components: {
    Issues,
    NewIssue
  },
  beforeCreate() {
    // this.$http.defaults.baseURL = 'http://localhost:8080/api'
    this.$http.defaults.baseURL = 'https://due-date-calculator.herokuapp.com/api'
  },
  methods: {
    openNewIssue () {
      this.showNewIssue = true
    }
  }
}
</script>
