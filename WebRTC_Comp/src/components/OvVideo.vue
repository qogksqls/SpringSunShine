<template>
	<video autoplay/>
</template>

<script>

export default {
	name: 'OvVideo',

	props: {
		streamManager: Object,
	},

	mounted () {
		this.streamManager.addVideoElement(this.$el);
	},
};
</script>

<style scoped>
	video {
		border: 5px;
	}
</style>