<template>
  <div>
    <v-list-tile>
      <v-list-tile-action style="margin-top: 0; padding-top: 0">
        <v-icon color="indigo">{{ icon }}</v-icon>
      </v-list-tile-action>

      <v-list-tile-content>
        <v-list-tile-title v-if="data != null">{{ data }}</v-list-tile-title>
        <v-list-tile-title v-if="data === null">Not specified</v-list-tile-title>
        <v-list-tile-sub-title>{{ text }}</v-list-tile-sub-title>
      </v-list-tile-content>
    </v-list-tile>
  </div>
</template>

<script>
export default {
  name: 'CardInfoRow',
  props: {
    data: {
      type: String,
      default: '',
      required: false
    },
    text: {
      type: String,
      default: '',
      required: true
    },
    icon: {
      type: String,
      default: '',
      required: true
    }
  }
}
</script>

<style scoped>

</style>
