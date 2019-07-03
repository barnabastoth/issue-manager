<template>
    <v-dialog v-model="_open" persistent max-width="600">
       <v-card>
           <v-toolbar dark color="primary">
               <v-btn icon flat @click="closeNewIssue()">
                   <v-icon>fas fa-times-circle</v-icon>
               </v-btn>
               <v-toolbar-title>Report an Issue</v-toolbar-title>
               <v-spacer></v-spacer>
               <v-toolbar-items>
                   <v-btn dark raised color="success" @click="saveIssue()">Save Issue</v-btn>
               </v-toolbar-items>
           </v-toolbar>
           <v-card class="ma-3 pa-3">
               <v-text-field
                       box
                       label="Name of the Issue"
                       prepend-icon="fas fa-file-signature"
                       v-model="newIssue.name">
               </v-text-field>
               <v-textarea
                       label="Description of the Issue"
                       v-model="newIssue.description"
                       box
                       prepend-icon="fas fa-file-alt"
               ></v-textarea>
               <v-select
                       :items="priorities"
                       v-model="newIssue.priority"
                       box
                       label="Priority of the Issue"
                       prepend-icon="fas fa-stopwatch"
               ></v-select>
           </v-card>
       </v-card>
    </v-dialog>
</template>

<script>
import {eventBus} from '../eventBus'

export default {
    name: 'NewIssue',
    props: ['open'],
    data () {
        return {
            newIssue: {
                name: null,
                description: null,
                priority: 'LOW'
            },
            priorities: [
                { text: 'LOW (32h)', value: 'LOW' },
                { text: 'MEDIUM (16h)', value: 'MEDIUM' },
                { text: 'HIGH (8h)', value: 'HIGH' },
            ]
        }
    },
    methods: {
        saveIssue () {
            this.$http.put('/issue/', this.newIssue).then(() => {
                this._open = false
                eventBus.$emit('refreshIssues')
            })
        },
        closeNewIssue () {
            this._open = false
        }
    },
    computed: {
        _open: {
            get () {
                return this.open
            },
            set (value) {
                this.$emit('update:open', value)
            }
        }
    }
}
</script>

<style scoped>

</style>