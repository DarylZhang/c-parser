#include <stddef.h>
#include <stdint.h>
#include <time.h>



void ast_jb_conf_default(struct ast_jb_conf *conf)
{
	conf->max_size = DEFAULT_SIZE;
	conf->resync_threshold = DEFAULT_RESYNC;
	ast_copy_string(conf->impl, "fixed", sizeof(conf->impl));
	conf->target_extra = DEFAULT_TARGET_EXTRA;
}
