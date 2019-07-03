<template>
    <div>
        <v-layout row wrap justify-start>
            <v-flex xs6 md4 lg3 v-for="issue in issues" :key="issue.id" class="pa-3">
                <v-card>
                    <v-toolbar dark flat :color="toolbarColor(issue.priority)">
                        <v-toolbar-title>
                            <v-icon left>fas fa-bug</v-icon>
                            <span>{{ issue.name }}</span>
                        </v-toolbar-title>
                    </v-toolbar>
                    <v-list>
                        <CardInfoRow icon="fas fa-file-signature" :data="issue.name" text="Name of the issue"></CardInfoRow>
                        <CardInfoRow icon="fas fa-file-alt" :data="issue.description" text="Description of the issue"></CardInfoRow>
                        <CardInfoRow icon="fas fa-stopwatch" :data="issue.priority" text="Priority of the issue"></CardInfoRow>
                        <CardInfoRow icon="fas fa-calendar" :data="issue.dueDate" text="Due date of the issue"></CardInfoRow>
                        <CardInfoRow icon="fas fa-clock" :data="issue.createdAt" text="Creation time of the issue"></CardInfoRow>
                    </v-list>
                    <v-card-actions>
                        <v-tooltip top>
                            <v-btn icon large class="mx-2 my-1" color="info" slot="activator">
                                <v-icon>fas fa-edit</v-icon>
                            </v-btn>
                            <span>Edit</span>
                        </v-tooltip>
                        <v-tooltip top>
                            <v-btn icon large class="mx-2 my-1" slot="activator" color="error">
                                <v-icon>fas fa-trash</v-icon>
                            </v-btn>
                            <span>Delete</span>
                        </v-tooltip>
                    </v-card-actions>
                </v-card>
            </v-flex>
        </v-layout>
    </div>
</template>

<script>
import CardInfoRow from './CardInfoRow'
import {eventBus} from '../eventBus'

export default {
    name: 'Issues',
    components: {
        CardInfoRow
    },
    beforeMount () {
        this.getIssues();
    },
    mounted () {
        eventBus.$on('refreshIssues', () => {
            this.getIssues()
        })
    },
    data () {
        return {
            issues: []
        }
    },
    methods: {
        getIssues () {
            this.$http.get('/issue/').then(response => { this.issues = response.data })
        },
        toolbarColor (priority) {
            if (priority === 'LOW') {
                return 'info'
            } else if (priority === 'MEDIUM') {
                return 'secondary'
            } else if (priority === 'HIGH') {
                return 'warning'
            }
        }
    }
}
</script>

<style scoped>

</style>