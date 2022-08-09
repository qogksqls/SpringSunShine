<template>
<div v-if="streamManager">
	<!-- <ov-video :widthOfVideo="widthOfVideo" :heightOfVideo="heightOfVideo" :stream-manager="streamManager"/> -->
	<ov-video :stream-manager="streamManager"/>
</div>
</template>

<script>
import OvVideo from './OvVideo';

export default {
	name: 'UserVideo',

	components: {
		OvVideo,
	},

	props: {
		streamManager: Object,
    // widthOfVideo: Number,
    // heightOfVideo: Number,
	},

	computed: {
		clientData () {
			const { clientData } = this.getConnectionData();
			return clientData;
		},
	},

	methods: {
		getConnectionData () {
			const { connection } = this.streamManager.stream;
			return JSON.parse(connection.data);
		},
	},
};
</script>

<style scoped>

</style>